        @Override
        public String toString()
        {
            return new StringBuffer().append("\n=== ").append(getClass().getSimpleName()).append(" ===")
                .append("\nname: ").append(_name)
                .append("\nint1: ").append(_int1)
                .append("\nint2: ").append(_int2)
                .append("\nlong1: ").append(_long1)
                .append("\nlong2: ").append(_long2)
                .append("\nfloat1: ").append(_float1)
                .append("\nfloat2: ").append(_float2)
                .append("\ndouble1: ").append(_double1)
                .append("\ndouble2: ").append(_double2)
                .toString();
        }
