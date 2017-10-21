    public Builder setBlah(org.springframework.protobuf.SecondMsg value) {
      if (blahBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        blah_ = value;
        onChanged();
      } else {
        blahBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000002;
      return this;
    }
