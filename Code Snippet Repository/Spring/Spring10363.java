    public Builder mergeFrom(org.springframework.protobuf.Msg other) {
      if (other == org.springframework.protobuf.Msg.getDefaultInstance()) return this;
      if (other.hasFoo()) {
        bitField0_ |= 0x00000001;
        foo_ = other.foo_;
        onChanged();
      }
      if (other.hasBlah()) {
        mergeBlah(other.getBlah());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      return this;
    }
