package com.udacity.jdnd.course1.mapper;

import com.udacity.jdnd.course1.model.ChatMessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessageMapper {
    @Insert("INSERT INTO MESSAGES (username, messagetext) VALUES (#{username}, #{messagetext})")
    @Options(useGeneratedKeys = true, keyProperty = "messageid")
    public int addMessage(ChatMessage chatMessage);

    @Select("SELECT * FROM MESSAGES")
    public List<ChatMessage> getMessages();
}
