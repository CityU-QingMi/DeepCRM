        public boolean next() {

            if (isBeforeFirst) {
                isBeforeFirst = false;
            } else {
                if (it == emptyIterator) {
                    return false;
                }
            }

            if (session.abortTransaction) {
                throw Error.error(ErrorCode.X_40000);
            }

            if (session.abortAction) {
                throw Error.error(ErrorCode.X_40502);
            }

            if (it.next()) {
                return true;
            } else {
                return false;
            }
        }
