        @Override
        public boolean equals(final Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Fixture other = (Fixture) obj;
            if (this.level == null) {
                if (other.level != null) {
                    return false;
                }
            } else if (!this.level.equals(other.level)) {
                return false;
            }
            return true;
        }
