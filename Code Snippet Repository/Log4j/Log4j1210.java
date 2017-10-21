    @Override
    public void log(final StatusData data) {
        final Notification notifMsg = new Notification(NOTIF_TYPE_MESSAGE, getObjectName(), nextSeqNo(), nowMillis(),
                data.getFormattedStatus());
        sendNotification(notifMsg);

        final Notification notifData = new Notification(NOTIF_TYPE_DATA, getObjectName(), nextSeqNo(), nowMillis());
        notifData.setUserData(data);
        sendNotification(notifData);
    }
