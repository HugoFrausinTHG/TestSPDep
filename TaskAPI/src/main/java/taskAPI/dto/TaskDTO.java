package taskAPI.dto;

import taskAPI.enums.STATUS;

public record TaskDTO(String id, String title, String description, STATUS status){

}
