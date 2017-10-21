        @Override
        public boolean equals(Object obj) {
            if (obj == null) { return false; }
            if (! (obj instanceof MyObject)) { return false; }
            MyObject tmp = (MyObject) obj;
            if (
                    tmp.name.equals(this.name) &&
                    tmp.age.equals(this.age)
                ) {
                return true;
            }
            return false;

        }
