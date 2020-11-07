package com.reckue.account.controller.api;

import com.reckue.account.transfer.UserTransfer;
import io.swagger.annotations.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Interface UserApi allows to post annotations for swagger.
 *
 * @author Kamila Meshcheryakova
 */
@Api(tags = {"/users"})
@SuppressWarnings("unused")
public interface UserApi {

    @ApiOperation(value = "View a list of available users", response = UserTransfer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of users successfully retrieved"),
            @ApiResponse(code = 400, message = "You need to change the parameters of your request"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    List<UserTransfer> getAll(int limit, int offset, String sort, boolean desc);

    @ApiOperation(value = "Get user by id", response = UserTransfer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The user successfully found"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    UserTransfer getById(String id);

    @ApiOperation(value = "Get user by username", response = UserTransfer.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The user successfully found"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    UserTransfer getByUsername(String username);

    @ApiOperation(value = "Delete user by id", authorizations = {@Authorization(value = "Bearer token")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The user successfully deleted"),
            @ApiResponse(code = 404, message = "The resource you were trying to delete is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    void deleteById(String id, HttpServletRequest request);

    @ApiOperation(value = "Delete user by username", authorizations = {@Authorization(value = "Bearer token")})
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The user successfully deleted"),
            @ApiResponse(code = 404, message = "The resource you were trying to delete is not found"),
            @ApiResponse(code = 500, message = "Access to the resource you tried to obtain is not possible")})
    void deleteByUsername(String username, HttpServletRequest request);
}