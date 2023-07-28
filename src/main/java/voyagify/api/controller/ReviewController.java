package voyagify.api.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voyagify.api.domain.review.ReviewDataDTO;
import voyagify.api.domain.review.ReviewService;
import voyagify.api.domain.review.ReviewInputDTO;
import voyagify.api.domain.review.ReviewRepository;
import voyagify.api.domain.user.UserService;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository repository;

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/add")
    @Transactional
    public ResponseEntity postReview(
            @RequestBody @Valid ReviewInputDTO data,
            @Autowired HttpServletRequest request) throws IOException {

        String authHeader = request.getHeader("Authorization");

        var dto = reviewService.create(data, authHeader);

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/random")
    public ResponseEntity<List<ReviewDataDTO>> getRandomReviews() {
        var page = reviewService.getRandomReviews();
        return ResponseEntity.ok(page);

    }
    @GetMapping("/user")
    public ResponseEntity<Page<ReviewDataDTO>> getReviewsByUserId(
            @PathVariable Long userId,
            @PageableDefault(size = 3, sort = "text") Pageable pageable) {
        Page<ReviewDataDTO> page = reviewService.getReviewsByUserId(userId, pageable);
        return ResponseEntity.ok(page);
    }
}
