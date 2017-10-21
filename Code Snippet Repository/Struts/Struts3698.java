        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof TempLink)) return false;

            final TempLink tempLink = (TempLink) o;

            if (typeResult != tempLink.typeResult) return false;
            if (label != null ? !label.equals(tempLink.label) : tempLink.label != null) return false;
            if (location != null ? !location.equals(tempLink.location) : tempLink.location != null) return false;
            if (node != null ? !node.equals(tempLink.node) : tempLink.node != null) return false;

            return true;
        }
