        public void rewind(int position) {

            if (baseContext != null) {
                baseContext.rewindRangeVariables(position);
                rewindParameters(position);

                return;
            }

            rewindRangeVariables(position);
            rewindParameters(position);
        }
