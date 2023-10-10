package app.vercel.misaellopes.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
  
  @Autowired
  private IUserRepository userRepository;
  
  @PostMapping("/")
  public ResponseEntity create(@RequestBody UserModel userModel) {
    var userAlreadyExists = this.userRepository.findByUsername(userModel.getUsername());

    if (userAlreadyExists != null) {
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Already Exists!");
    }

    var user = this.userRepository.save(userModel);
    return ResponseEntity.status(HttpStatus.CREATED).body(user);
  }
}
