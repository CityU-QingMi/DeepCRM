        public Object next() {

            if (counter < elementCount) {
                removed = false;

                Object returnValue = get(counter);

                counter++;

                return returnValue;
            }

            throw new NoSuchElementException();
        }
