        private void toNextLookup() {

            while (true) {
                lookup = nextLookup(lookup);

                if (lookup == -1 || !multiValueTable[lookup]) {
                    break;
                }
            }
        }
