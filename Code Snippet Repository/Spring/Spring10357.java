    public Builder mergeBlah(org.springframework.protobuf.SecondMsg value) {
      if (blahBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002) &&
            blah_ != org.springframework.protobuf.SecondMsg.getDefaultInstance()) {
          blah_ =
            org.springframework.protobuf.SecondMsg.newBuilder(blah_).mergeFrom(value).buildPartial();
        } else {
          blah_ = value;
        }
        onChanged();
      } else {
        blahBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000002;
      return this;
    }
