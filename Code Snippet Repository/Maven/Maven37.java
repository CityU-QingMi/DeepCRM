        public String toString()
        {
            StringBuilder buffer = new StringBuilder();
            for ( Item item : this )
            {
                if ( buffer.length() > 0 )
                {
                    buffer.append( ( item instanceof ListItem ) ? '-' : '.' );
                }
                buffer.append( item );
            }
            return buffer.toString();
        }
