package com.equator.netty.geekbang.common;

import com.equator.util.JsonUtil;
import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.nio.charset.Charset;

/**
 * @Author: Equator
 * @Date: 2022/2/2 23:59
 **/
@Data
public abstract class Message<T extends MessageBody> {

    private MessageHeader messageHeader;

    private T messageBody;

    public void encode(ByteBuf byteBuf) {
        byteBuf.writeInt(messageHeader.getVersion());
        byteBuf.writeLong(messageHeader.getStreamId());
        byteBuf.writeInt(messageHeader.getOpCode());
        byteBuf.writeBytes(JsonUtil.toJson(messageBody).getBytes());
    }

    /**
     * @param opCode
     * @return
     */
    public abstract Class<T> getMessageBodyDecodeClass(int opCode);

    public void decode(ByteBuf byteBuf) {
        int version = byteBuf.readInt();
        long streamId = byteBuf.readLong();
        int opCode = byteBuf.readInt();
        MessageHeader header = new MessageHeader();
        header.setVersion(version);
        header.setStreamId(streamId);
        header.setOpCode(opCode);

        Class<T> bodyClazz = getMessageBodyDecodeClass(opCode);
        this.messageHeader = header;
        this.messageBody = JsonUtil.fromJson(byteBuf.toString(Charset.forName("UTF-8")), bodyClazz);
    }


}
