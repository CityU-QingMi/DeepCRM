        @Override
        protected Action process() throws Exception
        {
            DataInfo dataInfo;
            synchronized (this)
            {
                dataInfo = queue.poll();
            }

            if (dataInfo == null)
            {
                DataInfo prevDataInfo = this.dataInfo;
                if (prevDataInfo != null && prevDataInfo.last)
                    return Action.SUCCEEDED;
                return Action.IDLE;
            }

            this.dataInfo = dataInfo;
            responseContent(dataInfo.exchange, dataInfo.buffer, this);
            return Action.SCHEDULED;
        }
