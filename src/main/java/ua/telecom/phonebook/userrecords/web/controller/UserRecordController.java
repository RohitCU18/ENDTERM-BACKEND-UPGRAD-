package ua.telecom.phonebook.userrecords.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.telecom.phonebook.userrecords.model.UserRecord;
import ua.telecom.phonebook.userrecords.model.dto.UserRecordTo;
import ua.telecom.phonebook.userrecords.service.UserRecordService;
import ua.telecom.phonebook.users.AuthorizedUser;
import ua.telecom.phonebook.util.UserRecordUtil;
import ua.telecom.phonebook.util.ValidationUtil;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;


@RestController
@RequestMapping(value = "/user/records")
public class UserRecordController {

    private final UserRecordService userRecordService;

    @Autowired
    public UserRecordController(UserRecordService userRecordService) {
        this.userRecordService = userRecordService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserRecordTo> getUserRecords() {
        int userId = AuthorizedUser.id();
        return UserRecordUtil.getListUserRecordsTo(userRecordService.getAll(userId));
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserRecordTo getRecord(@PathVariable int id) {
        int userId = AuthorizedUser.id();
        return UserRecordUtil.getUserRecordTo(userRecordService.get(id, userId));
    }

    @PostMapping
    public ResponseEntity<String> createOrUpdate(@Valid UserRecordTo record, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(ValidationUtil.getErrorResponse(bindingResult));
        }

        int userId = AuthorizedUser.id();
        UserRecord nativeRecord = UserRecordUtil.getUserRecord(record);
        if (nativeRecord.isNew()) {
            userRecordService.save(nativeRecord, userId);
        } else {
            userRecordService.update(nativeRecord, userId);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id) {
        int userId = AuthorizedUser.id();
        userRecordService.delete(id, userId);
        return ResponseEntity.noContent().build();
    }
}
