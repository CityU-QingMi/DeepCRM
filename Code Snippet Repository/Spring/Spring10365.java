    public java.lang.String getFoo() {
      java.lang.Object ref = foo_;
      if (!(ref instanceof java.lang.String)) {
        java.lang.String s = ((com.google.protobuf.ByteString) ref)
            .toStringUtf8();
        foo_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
