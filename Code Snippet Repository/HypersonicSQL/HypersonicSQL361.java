        public void decrementDepth() {

            clearSubqueries();

            subqueryDepth--;

            if (baseContext != null) {
                baseContext.subqueryDepth--;
            }
        }
