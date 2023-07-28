package voyagify.api.domain.review;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import voyagify.api.domain.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewDataDTO create(ReviewInputDTO data, Long userId) {

        if (!userRepository.existsById(userId)) {
            throw new ValidationException("User does not exist");
        }

        var user = userRepository.findUserById(userId);

        var name = user.getName();
        var textReview = data.text();

        var review = new Review(null, user, data.text());

        reviewRepository.save(review);

        var id = review.getId();

        return new ReviewDataDTO(id, name, textReview);
    }

    //NOTE: This method should always return 3 reviews to be called when the page is displayed.
    public List<ReviewDataDTO> getRandomReviews() {
        Pageable pageable = PageRequest.of(0, 3);
        Page<Review> page = reviewRepository.findRandomReviews(pageable);
        return page.stream()
                .map(review -> new ReviewDataDTO(review.getId(), review.getUser().getName(), review.getText()))
                .collect(Collectors.toList());
    }

    public Page<ReviewDataDTO> getReviewsByUserId(Long userId, Pageable pageable) {
        System.out.println(userId);
        Page<Review> reviewPage = reviewRepository.findAllByUserId(userId, pageable);
        return reviewPage.map(review -> new ReviewDataDTO(review.getId(), review.getUser().getName(), review.getText()));
    }
}
