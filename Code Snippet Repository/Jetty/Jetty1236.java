        @Override
        public void onClose(Session session, GoAwayFrame frame, Callback callback)
        {
            ErrorCode error = ErrorCode.from(frame.getError());
            if (error == null)
                error = ErrorCode.STREAM_CLOSED_ERROR;
            String reason = frame.tryConvertPayload();
            if (reason != null && !reason.isEmpty())
                reason = " (" + reason + ")";
            getConnection().onSessionFailure(new EofException("HTTP/2 " + error + reason), callback);
        }
