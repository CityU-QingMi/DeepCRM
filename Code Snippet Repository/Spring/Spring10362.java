    public org.springframework.protobuf.Msg buildPartial() {
      org.springframework.protobuf.Msg result = new org.springframework.protobuf.Msg(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.foo_ = foo_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      if (blahBuilder_ == null) {
        result.blah_ = blah_;
      } else {
        result.blah_ = blahBuilder_.build();
      }
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }
