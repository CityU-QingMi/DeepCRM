        public String format( long size, ScaleUnit unit, boolean omitSymbol )
        {
            Validate.isTrue( size >= 0L, "file size cannot be negative: %s", size );

            if ( unit == null )
            {
                unit = ScaleUnit.getScaleUnit( size );
            }

            double scaledSize = (double) size / unit.bytes();
            String scaledSymbol = " " + unit.symbol();

            if ( omitSymbol )
            {
                scaledSymbol = "";
            }

            if ( unit == ScaleUnit.BYTE )
            {
                return largeFormat.format( size ) + scaledSymbol;
            }

            if ( scaledSize < 0.05 || scaledSize >= 10.0 )
            {
                return largeFormat.format( scaledSize ) + scaledSymbol;
            }
            else
            {
                return smallFormat.format( scaledSize ) + scaledSymbol;
            }
        }
