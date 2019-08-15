package com.institute.bi.tierc.user.exception;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.institute.bi.domain.user.request.User;
import com.institute.bi.domain.user.request.User.UserBuilder;

import lombok.Builder;
import lombok.Data;

/**
 * @author bijendra
 *
 */
@Builder
@Data
@JsonInclude(Include.NON_EMPTY)
public class ErrorResponse {
	private List<ErrorItem> erroritems;
	private Integer status;

}
