        public boolean next() {

            if (session.abortTransaction) {
                throw Error.error(ErrorCode.X_40000);
            }

            if (session.abortAction) {
                throw Error.error(ErrorCode.X_40502);
            }

            while (condIndex < conditions.length) {
                if (isBeforeFirst) {
                    isBeforeFirst = false;

                    initialiseIterator();
                }

                boolean result = findNext();

                if (result) {
                    return true;
                }

                reset();

                condIndex++;
            }

            condIndex = 0;

            return false;
        }
