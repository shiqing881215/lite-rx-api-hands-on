package io.pivotal.literx;

import io.pivotal.literx.domain.User;
import io.pivotal.literx.repository.BlockingRepository;
import io.pivotal.literx.repository.BlockingUserRepository;
import io.pivotal.literx.repository.ReactiveStarWarsUsersRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Good old Java
 *
 * @author Julien Hoarau
 *
 * @see java.util.concurrent.Future
 * @see java.util.concurrent.ExecutorService
 * @see java.util.concurrent.CompletableFuture
 *
 */
public class Part00WithoutReactive {

  private BlockingRepository<User> userRepository = new BlockingUserRepository();
  private BlockingRepository<User> starWarsCharacterRepository = new BlockingUserRepository(new ReactiveStarWarsUsersRepository());

  // TODO Change this method so we call both findAll() in parallel using Future/CompletableFuture
  public List<User> findAllUsersAsStarWarsCharacter() {
    final Iterable<User> characters = starWarsCharacterRepository.findAll();
    final Iterable<User> users = userRepository.findAll();

    return toStarWarsUsers(characters, users);
  }

  private List<User> toStarWarsUsers(Iterable<User> characters, Iterable<User> users) {
    Iterator<User> characterIterator = characters.iterator();
    final ArrayList<User> results = new ArrayList<>();
    for (User user : users) {
      if (!characterIterator.hasNext()) {
        characterIterator = characters.iterator();
      }

      if (characterIterator.hasNext()) {
        results.add(toStarWarsUser(user, characterIterator.next()));
      }
    }

    return results;
  }

  private User toStarWarsUser(User user, User character) {
    return new User(user.getUsername(), character.getFirstname(), user.getLastname());
  }
}
