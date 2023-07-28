package voyagify.api.domain.review;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import voyagify.api.domain.user.User;
import voyagify.api.domain.user.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    public ReviewDataDTO create(ReviewInputDTO data){
        if(!userRepository.existsById(data.idUser())){
            throw new ValidationException("User does not exist");
        }

        var user = userRepository.findById(data.idUser()).get();

        var name = user.getName();
        var textReview = data.text();

        var review = new Review(null, user, data.text());

        reviewRepository.save(review);

        return new ReviewDataDTO(name, textReview);
    }

    public List<ReviewDataDTO> getRandomReviews() {
        Pageable pageable = PageRequest.of(0, 3);
        Page<Review> page = reviewRepository.findRandomReviews(pageable);
        return page.stream()
                .map(review -> new ReviewDataDTO(review.getUser().getName(), review.getText()))
                .collect(Collectors.toList());
    }

    public Page<ReviewDataDTO> getReviewsByUserId(Long userId, Pageable pageable) {
        Page<Review> reviewPage = reviewRepository.findByUserId(userId, pageable);
        return reviewPage.map(review -> new ReviewDataDTO(review.getUser().getName(), review.getText()));
    }
}
