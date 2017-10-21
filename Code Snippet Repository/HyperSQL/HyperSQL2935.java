        public void registerRangeVariable(RangeVariable range) {

            int parsePosition = parser == null ? 0
                                               : parser.getPosition();

            range.rangePosition = getNextRangeVarIndex();
            range.level         = subqueryDepth;

            rangeVariables.put(parsePosition, range);
        }
