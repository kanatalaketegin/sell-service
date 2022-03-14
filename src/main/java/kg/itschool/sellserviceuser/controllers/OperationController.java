package kg.itschool.sellserviceuser.controllers;

import kg.itschool.sellserviceuser.models.dtos.InputDataForOperation;
import kg.itschool.sellserviceuser.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/operation")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @PostMapping("/provideOperation")
    public ResponseEntity<?> provideOperation(@RequestHeader String token, @RequestBody List<InputDataForOperation> sellingList ) {
        return operationService.provideOperation(token, sellingList);
    }

    @GetMapping("/payment")
    public ResponseEntity<?> payment(@RequestHeader String token, @RequestParam Long operationId, @RequestParam double cash) {
        return operationService.payment(token, operationId, cash);
    }
}
