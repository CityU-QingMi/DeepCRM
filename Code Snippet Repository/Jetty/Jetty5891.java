        @Override
        public boolean remove(Object o)
        {
            if (!(o instanceof Entry))
                return false;

            boolean removedPackage = _byPackage.remove(o);
            boolean removedClass = _byClass.remove(o);

            return removedPackage || removedClass;
        }
