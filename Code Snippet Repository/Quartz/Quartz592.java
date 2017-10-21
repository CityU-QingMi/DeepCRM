        @SuppressWarnings("")
        @Override
        public <U> U[] toArray(final U[] array) {
            if (array.getClass().getComponentType().isAssignableFrom(Map.Entry.class) == false) {
                throw new IllegalArgumentException("Array must be of type assignable from Map.Entry");
            }

            int size = super.size();

            U[] result =
                array.length < size ?
                    (U[])Array.newInstance(array.getClass().getComponentType(), size) : array;

            Iterator<Map.Entry<K,V>> entryIter = iterator(); // Will return DirtyFlagMapEntry objects
            for (int i = 0; i < size; i++) {
                result[i] = ( U ) entryIter.next();
            }

            if (result.length > size) {
                result[size] = null;
            }

            return result;
        }
