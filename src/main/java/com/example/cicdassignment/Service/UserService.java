package com.example.cicdassignment.Service;

import com.example.cicdassignment.DTO.UserDTO;
import com.example.cicdassignment.DTO.UserSummaryDTO;
import com.example.cicdassignment.Entity.User;
import com.example.cicdassignment.Repository.UserRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<UserSummaryDTO> findAll(Pageable pageable){
        List<User> users = userRepository.findAll(pageable).getContent();

        return users.stream()
                .map(user -> new UserSummaryDTO(
                        user.getUserId(),
                        user.getFirstName(),
                        user.getLastName()
                ))
                .toList();
    }

    public UserDTO getUserById(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"user not found"));
//        Profile profile = user.getProfile();
//
//        ProfileDTO profileDTO = null;
//        if(profile != null){
//            profileDTO = new ProfileDTO(
//                    profile.getUserProfileId(),
//                    profile.getUserWeight(),
//                    profile.getUserHeight()
//            );
//        }

//        List<WorkoutDTO> workoutDTOs = user.getWorkouts().stream()
//                .sorted((w1, w2) -> w2.getWorkoutDate().compareTo(w1.getWorkoutDate()))
//                .map(w -> new WorkoutDTO(
//                        w.getWorkoutId(),
//                        w.getWorkoutTitle(),
//                        w.getWorkoutDate(),
//                        w.getOptionalNotes()
//                ))
//                .toList();

        return new UserDTO(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName()
//                profileDTO,
//                workoutDTOs
        );
    }

    public UserDTO savedUser(UserDTO userDTO){
        User user = new User();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

        // handling profile
//        if(userDTO.getProfile() != null){
//            Profile profile = new Profile();
//            profile.setUserWeight(userDTO.getProfile().getUserWeight());
//            profile.setUserHeight(userDTO.getProfile().getUserHeight());
//            user.setProfile(profile);
//        }

        // handling workouts
//        if(userDTO.getWorkouts() != null){
//            List<Workout> workouts = userDTO.getWorkouts().stream()
//                    .map(workoutDTO -> {
//                        Workout workout = new Workout();
//                        workout.setWorkoutTitle(workoutDTO.getWorkoutTitle());
//                        workout.setWorkoutDate(workoutDTO.getWorkoutDate());
//                        workout.setOptionalNotes(workoutDTO.getOptionalNotes());
//                        workout.setUser(user);
//                        return workout;
//                    }).toList();
//        }

        User savedUser = userRepository.save(user);
        return mapToDTO(savedUser);
    }


    public UserDTO updateUser(Long id, UserDTO userDTO){
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"user not found"));

        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());

//        if(userDTO.getProfile() != null){
//            Profile profile = user.getProfile();
//            if(profile == null){
//                profile = new Profile();
//            }
//            profile.setUserWeight(userDTO.getProfile().getUserWeight());
//            profile.setUserHeight(userDTO.getProfile().getUserHeight());
//            user.setProfile(profile);
//        }

        User savedUser = userRepository.save(user);
//        ProfileDTO profileDTO = null;

//        if(savedUser.getProfile() != null){
//            profileDTO = new ProfileDTO(
//                    savedUser.getProfile().getUserProfileId(),
//                    savedUser.getProfile().getUserWeight(),
//                    savedUser.getProfile().getUserHeight()
//            );
//        }

//        List<WorkoutDTO> workoutDTO = new ArrayList<>();

//        if(user.getWorkouts() != null){
//            workoutDTO = user.getWorkouts().stream()
//                    .map(workout -> new WorkoutDTO(
//                            workout.getWorkoutId(),
//                            workout.getWorkoutTitle(),
//                            workout.getWorkoutDate(),
//                            workout.getOptionalNotes()
//                    )).toList();
//        }

        return new UserDTO(
                savedUser.getUserId(),
                savedUser.getFirstName(),
                savedUser.getLastName()
//                profileDTO,
//                workoutDTO
        );
    }

    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"user not found"));
        userRepository.delete(user);
    }

//    public List<WorkoutDTO> getWorkoutsForUser(Long id){
//        User user = userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"user not found"));
//        return user.getWorkouts().stream()
//                .sorted((w1, w2) -> w2.getWorkoutDate().compareTo(w1.getWorkoutDate()))
//                .map(workout -> new WorkoutDTO(
//                        workout.getWorkoutId(),
//                        workout.getWorkoutTitle(),
//                        workout.getWorkoutDate(),
//                        workout.getOptionalNotes()
//                )).toList();
//    }

    private UserDTO mapToDTO(User user) {
//        ProfileDTO profileDTO = null;
//
//        if (user.getProfile() != null) {
//            Profile profile = user.getProfile();
//
//            profileDTO = new ProfileDTO(
//                    profile.getUserProfileId(),
//                    profile.getUserWeight(),
//                    profile.getUserHeight()
//            );
//        }

//        List<WorkoutDTO> workoutDTOs = null;

//        if (user.getWorkouts() != null) {
//            workoutDTOs = user.getWorkouts().stream()
//                    .map(workout -> new WorkoutDTO(
//                            workout.getWorkoutId(),
//                            workout.getWorkoutTitle(),
//                            workout.getWorkoutDate(),
//                            workout.getOptionalNotes()
//                    ))
//                    .toList();
//        }

        return new UserDTO(
                user.getUserId(),
                user.getFirstName(),
                user.getLastName()
//                profileDTO,
//                workoutDTOs
        );
    }
}

