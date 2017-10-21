        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeUTF((this.prefix != null) ? this.prefix : "");
            out.writeUTF(this.localName);
            // make sure m isn't null
            getMethod();
            out.writeUTF((this.owner != null) ?
                     this.owner :
                     this.m.getDeclaringClass().getName());
            out.writeUTF((this.name != null) ?
                     this.name :
                     this.m.getName());
            out.writeObject((this.types != null) ?
                     this.types :
                     ReflectionUtil.toTypeNameArray(this.m.getParameterTypes()));

        }
