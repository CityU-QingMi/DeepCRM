        public NumberSequence[] getSequences() {

            if (usedSequences.size() == 0) {
                return NumberSequence.emptyArray;
            }

            NumberSequence[] array = new NumberSequence[usedSequences.size()];

            usedSequences.toArray(array);

            return array;
        }
