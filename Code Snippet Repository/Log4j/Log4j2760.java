    private void handleNotificationInAwtEventThread(final Notification notif, final Object paramObject) {
        if (StatusLoggerAdminMBean.NOTIF_TYPE_MESSAGE.equals(notif.getType())) {
            if (!(paramObject instanceof ObjectName)) {
                handle("Invalid notification object type", new ClassCastException(paramObject.getClass().getName()));
                return;
            }
            final ObjectName param = (ObjectName) paramObject;
            final JTextArea text = statusLogTextAreaMap.get(param);
            if (text != null) {
                text.append(notif.getMessage() + '\n');
            }
            return;
        }
        if (notif instanceof MBeanServerNotification) {
            final MBeanServerNotification mbsn = (MBeanServerNotification) notif;
            final ObjectName mbeanName = mbsn.getMBeanName();
            if (MBeanServerNotification.REGISTRATION_NOTIFICATION.equals(notif.getType())) {
                onMBeanRegistered(mbeanName);
            } else if (MBeanServerNotification.UNREGISTRATION_NOTIFICATION.equals(notif.getType())) {
                onMBeanUnregistered(mbeanName);
            }
        }
    }
