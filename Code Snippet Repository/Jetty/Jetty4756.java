        @Override
        public boolean equals(Object another)
        {
            if(another instanceof Foo)
            {
                Foo foo = (Foo)another;
                return getName().equals(foo.getName())
                    && getInt1()==foo.getInt1()
                    && getInt2().equals(foo.getInt2())
                    && getLong1()==foo.getLong1()
                    && getLong2().equals(foo.getLong2())
                    && getFloat1()==foo.getFloat1()
                    && getFloat2().equals(foo.getFloat2())
                    && getDouble1()==foo.getDouble1()
                    && getDouble2().equals(foo.getDouble2())
                    && getChar1()==foo.getChar1()
                    && getChar2().equals(foo.getChar2());
            }

            return false;
        }
