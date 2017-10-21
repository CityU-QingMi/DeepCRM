    public Builder setBlah(
        org.springframework.protobuf.SecondMsg.Builder builderForValue) {
      if (blahBuilder_ == null) {
        blah_ = builderForValue.build();
        onChanged();
      } else {
        blahBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000002;
      return this;
    }
