        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof EqualFilter)) {
                return false;
            }

            final EqualFilter that = (EqualFilter) o;

            if (key != null ? !key.equals(that.key) : that.key != null) {
                return false;
            }

            return true;
        }
