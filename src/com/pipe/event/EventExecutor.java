package com.pipe.event;

import java.lang.reflect.Method;
import java.util.*;

public class EventExecutor {

    // TODO CHECK PERFORMANCE AND IMPLEMENT PRIORITY

    private static Map<Class, Map<Object, Method[]>> eventMap = new HashMap<>();

    public static void callEvent(Event event) {
        Map<Object, Method[]> listenerMap = eventMap.get(event.getClass());

        if (listenerMap == null) {
            return;
        }

        for (Map.Entry<Object, Method[]> entryListener : listenerMap.entrySet()) {
            Object listener = entryListener.getKey();
            Method[] handlers = entryListener.getValue();

            try {
                for (Method handler : handlers) {
                    handler.invoke(listener, event);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void registerListener(Listener listener) {
        Class<?> listenerClass = listener.getClass();

        for (Method method : listenerClass.getDeclaredMethods()) {
            if (method.getParameterCount() == 1) {
                Class<?> parameterClass = method.getParameters()[0].getType();

                if (Event.class.isAssignableFrom(parameterClass) && method.isAnnotationPresent(EventHandler.class)) {
                    Map<Object, Method[]> listenerMap = eventMap.get(parameterClass);
                    Method[] methods;

                    if (listenerMap == null) {
                        listenerMap = new HashMap<>();
                        methods = new Method[]{method};

                    } else {
                        methods = (Method[]) Arrays.asList(listenerMap.get(method), method).toArray();
                    }

                    listenerMap.put(listener, methods);
                }
            }
        }
    }
}
