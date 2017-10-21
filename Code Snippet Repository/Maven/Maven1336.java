        public String formatProgress( long progressedSize, long size )
        {
            Validate.isTrue( progressedSize >= 0L, "progressed file size cannot be negative: %s", progressedSize );
            Validate.isTrue( size >= 0L && progressedSize <= size || size < 0L,
                "progressed file size cannot be greater than size: %s > %s", progressedSize, size );

            if ( size >= 0L && progressedSize != size )
            {
                ScaleUnit unit = ScaleUnit.getScaleUnit( size );
                String formattedProgressedSize = format( progressedSize, unit, true );
                String formattedSize = format( size, unit );

                return formattedProgressedSize + "/" + formattedSize;
            }
            else
            {
                return format( progressedSize );
            }
        }
