    void logSaveRowsEvent(int saveCount, long storageSize, long startTime) {

        long         time = saveAllTimer.elapsedTime();
        StringBuffer sb   = new StringBuffer();

        sb.append("cache save rows total [count,time] ");
        sb.append(saveRowCount + saveCount);
        sb.append(',').append(time).append(' ');
        sb.append("operation [count,time,size]").append(saveCount).append(',');
        sb.append(time - startTime).append(',');
        sb.append(storageSize).append(' ');

//
        sb.append("tx-ts ");
        sb.append(dataFileCache.database.txManager.getGlobalChangeTimestamp());

//
        dataFileCache.logDetailEvent(sb.toString());
    }
