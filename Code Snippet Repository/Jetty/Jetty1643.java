        @Override
        public InvocationType getInvocationType()
        {
            InvocationType fillT = getFillInterest().getCallbackInvocationType();
            InvocationType flushT = getWriteFlusher().getCallbackInvocationType();
            if (fillT==flushT)
                return fillT;
            
            if (fillT==InvocationType.EITHER && flushT==InvocationType.NON_BLOCKING)
                return InvocationType.EITHER;
            
            if (fillT==InvocationType.NON_BLOCKING && flushT==InvocationType.EITHER)
                return InvocationType.EITHER;
            
            return InvocationType.BLOCKING;
        }
