        public boolean equals(Field that, boolean caseSensitive)
        {
            if (this == that)
                return true;
            if (that == null)
                return false;
            if (caseSensitive)
                return equals(that);
            return name.equalsIgnoreCase(that.name) && values.equals(that.values);
        }
