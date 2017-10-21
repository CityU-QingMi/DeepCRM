        public void setNextRangeVarIndex(int n) {

            if (baseContext != null) {
                baseContext.setNextRangeVarIndex(n);

                return;
            }

            rangeVarIndex = n;
        }
