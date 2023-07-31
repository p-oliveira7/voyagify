package voyagify.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voyagify.api.domain.review.ReviewResponseDTO;
import voyagify.api.domain.review.ReviewService;
import voyagify.api.domain.review.ReviewDataDTO;
import voyagify.api.domain.review.ReviewRepository;
import voyagify.api.domain.user.User;
import voyagify.api.infra.security.SecurityUtils;

import java.util.List;

import static voyagify.api.infra.security.SecurityUtils.getAuthenticatedUser;

@RestController
@RequestMapping("reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    @Transactional
    public ResponseEntity postReview(@RequestBody @Valid ReviewDataDTO data){

        var user = getAuthenticatedUser();

       var dto = reviewService.create(data, user);

       return ResponseEntity.ok(dto);
    }

    @GetMapping("/random")
    public ResponseEntity<List<ReviewResponseDTO>> getRandomReviews() {
        var page = reviewService.getRandomReviews();
        return ResponseEntity.ok(page);

    }
    @GetMapping("/user")
    public ResponseEntity<Page<ReviewResponseDTO>> getReviewsByUserId(
            @PageableDefault(size = 3, sort = "text") Pageable pageable) {

        var user = getAuthenticatedUser();

        Page<ReviewResponseDTO> page = reviewService.getReviewsByUserId(user, pageable);
        return ResponseEntity.ok(page);
    }
}
