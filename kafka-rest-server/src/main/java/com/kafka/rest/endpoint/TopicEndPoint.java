package com.kafka.rest.endpoint;

import com.kafka.api.model.result.Message;
import com.kafka.api.model.result.Result;
import com.nagendra.kafka.producer.TopicService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/topic")
public class TopicEndPoint {

    @GET
    @Path("/createTopic/{topicName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Result<Boolean> createTopic(@PathParam("topicName") String topicName) {
        Result<Boolean> result;
        try {
            boolean topicCreated = TopicService.addTopic(topicName);
            if(topicCreated) {
                result = Result.resultOf(true, Message.info("Topic created Successfully.."));
            } else {
                result = Result.resultOf(false, Message.error("Failed to create Topic " + topicName));
            }
        } catch (Exception e) {
            result = Result.resultOf(false, Message.error("Failed to create Topic " + topicName));
        }
        System.out.println("result is ..." + result.toString());
        return result;
    }

    @GET
    @Path("/deleteTopic/{topicName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Result<Boolean> deleteTopic(@PathParam("topicName") String topicName) {
        Result<Boolean> result;
        try {
            boolean topicDeleted = TopicService.deleteTopic(topicName);
            if(topicDeleted) {
                result = Result.resultOf(true, Message.info("Topic deleted Successfully.."));
            } else {
                result = Result.resultOf(false, Message.error("Failed to delete Topic " + topicName));
            }
        } catch (Exception e) {
            result = Result.resultOf(false, Message.error("Failed to delete Topic " + topicName));
        }
        return result;
    }
}
