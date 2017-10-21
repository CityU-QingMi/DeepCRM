    public org.springframework.protobuf.SecondMsg buildPartial() {
      org.springframework.protobuf.SecondMsg result = new org.springframework.protobuf.SecondMsg(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.blah_ = blah_;
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }
