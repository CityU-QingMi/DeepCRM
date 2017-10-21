    public Builder clear() {
      super.clear();
      foo_ = "";
      bitField0_ = (bitField0_ & ~0x00000001);
      if (blahBuilder_ == null) {
        blah_ = org.springframework.protobuf.SecondMsg.getDefaultInstance();
      } else {
        blahBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }
