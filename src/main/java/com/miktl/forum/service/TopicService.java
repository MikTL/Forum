package com.miktl.forum.service;

import com.miktl.forum.domain.course.Course;
import com.miktl.forum.domain.topic.Topic;
import com.miktl.forum.domain.user.User;
import com.miktl.forum.dto.topic.DataToRegisterTopic;
import com.miktl.forum.dto.topic.DataToUpdateTopic;
import com.miktl.forum.dto.topic.RegisteredDataTopic;
import com.miktl.forum.dto.topic.TopicListingData;
import com.miktl.forum.repository.course.CourseRepository;
import com.miktl.forum.repository.topic.TopicRepository;
import com.miktl.forum.repository.user.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
    private final TopicRepository topicRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public TopicService(
            TopicRepository topicRepository,
            UserRepository userRepository,
            CourseRepository courseRepository
    ) {
        this.topicRepository = topicRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public RegisteredDataTopic createTopic(DataToRegisterTopic dataToRegisterTopic){
        Topic topic= topicRepository.save(new Topic(dataToRegisterTopic));
        User user = userRepository.findById(topic.getId_user()).orElse(null);
        String authorName= (user!=null)? user.getName() : null;
        Course course = courseRepository.findById(topic.getId_course()).orElse(null);
        String courseName=(course!=null)?course.getName():null;
        return new RegisteredDataTopic(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate(),
                topic.getStatusTopic(),
                authorName,
                courseName
        );
    }
    public Page<TopicListingData>ListTopics(Pageable pageable){
        return topicRepository.findAllWithAuthorAndCourse(pageable);
    }
    public RegisteredDataTopic viewTopic(Long id) {
        Topic topic= topicRepository.getReferenceById(id);
        User user = userRepository.findById(topic.getId_user()).orElse(null);
        String authorName= (user!=null)? user.getName() : null;
        Course course = courseRepository.findById(topic.getId_course()).orElse(null);
        String courseName=(course!=null)?course.getName():null;
        RegisteredDataTopic dataTopic= new RegisteredDataTopic(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate(),
                topic.getStatusTopic(),
                authorName,
                courseName
        );
        return dataTopic;
    }

    public RegisteredDataTopic updateTopic(Long id,DataToUpdateTopic dataToUpdateTopic) {
        Topic topic= topicRepository.getReferenceById(id);
        topic.updateData(dataToUpdateTopic);
        User user = userRepository.findById(topic.getId_user()).orElse(null);
        String authorName= (user!=null)? user.getName() : null;
        Course course = courseRepository.findById(topic.getId_course()).orElse(null);
        String courseName=(course!=null)?course.getName():null;
        return new RegisteredDataTopic(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate(),
                topic.getStatusTopic(),
                authorName,
                courseName
        );
    }

    public void deleteTopic(Long id) {
        Topic topic = topicRepository.getReferenceById(id);
        topicRepository.delete(topic);
    }

}
