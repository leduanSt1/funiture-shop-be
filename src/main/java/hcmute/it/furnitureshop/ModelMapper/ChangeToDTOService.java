package hcmute.it.furnitureshop.ModelMapper;

import hcmute.it.furnitureshop.DTO.*;
import hcmute.it.furnitureshop.Entity.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Destination;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ChangeToDTOService {
    @Autowired
    ModelMapper modelMapper;
    public UserDTO changeUserToDTO(Optional<User> user){
        UserDTO userDTO=new UserDTO();
        if(user.isPresent()){
            userDTO = modelMapper.map(user.get(),UserDTO.class);
        }
        return userDTO;
    }
    public ArrayList<OrderDTO> changeListOrderToDTO(Iterable<Order> orders){
        ArrayList<OrderDTO> orderDTOS = new ArrayList<>();
        orders.forEach(order -> {
            OrderDTO orderDTO=modelMapper.map(order,OrderDTO.class);
            orderDTOS.add(orderDTO);
        });
        return orderDTOS;
    }
    public ArrayList<CategoryDTO> changeListCategoryToDTO(Iterable<Category> categories){
        ArrayList<CategoryDTO> categoryDTOS=new ArrayList<>();
        categories.forEach(category->{
            categoryDTOS.add(modelMapper.map(category,CategoryDTO.class));
        });
        return categoryDTOS;
    }

    public ArrayList<ProductDTO> changeListProductToDTO(Iterable<Product> products){
        ArrayList<ProductDTO> productDTOS=new ArrayList<>();
        products.forEach(product -> {
            productDTOS.add(modelMapper.map(product,ProductDTO.class));
        });
        return productDTOS;
    }

    public ProductDTO changeProductToDTO(Product product){
        ProductDTO productDTO = modelMapper.map(product,ProductDTO.class);
        return productDTO;
    }

    public CategoryDTO changeCategoryToDTO(Category category){
        CategoryDTO categoryDTO = modelMapper.map(category,CategoryDTO.class);
        return categoryDTO;
    }

    public RatingDTO changeRatingToDTO(Optional<Rating> ratingCurrent){
        if(ratingCurrent.isPresent()){
            Rating rating = ratingCurrent.get();
            RatingDTO ratingDTO=new RatingDTO();
            ratingDTO.setRatingId(rating.getRatingId());
            ratingDTO.setProductId(rating.getProduct().getProductId());
            ratingDTO.setUserId(rating.getUser().getUserId());
            ratingDTO.setPoint(rating.getPoint());
            return ratingDTO;
        }
        return new RatingDTO();
    }
    public ArrayList<FavoriteDTO> changeListFavoriteToDTO(Iterable<Favorite> favorites){
        ArrayList<FavoriteDTO> favoriteDTOS=new ArrayList<>();
        favorites.forEach(favorite -> {
            FavoriteDTO favoriteDTO=new FavoriteDTO();
            favoriteDTO.setFavoriteId(favorite.getFavoriteId());
            favoriteDTO.setUserId(favorite.getUser().getUserId());
            favoriteDTOS.add(favoriteDTO);
        });
        return  favoriteDTOS;
    }

    public ArrayList<ReviewDTO> changeListReviewToDTO(Iterable<Review> reviews){
        ArrayList<ReviewDTO> reviewDTOS=new ArrayList<>();
        reviews.forEach(review -> {
            ReviewDTO reviewDTO=new ReviewDTO();
            reviewDTO.setReviewId(review.getReviewId());
            reviewDTO.setDate(review.getDate());
            reviewDTO.setContent(review.getContent());
            reviewDTO.setName(review.getUser().getName());
            reviewDTO.setUserId(review.getUser().getUserId());
            reviewDTO.setImage(review.getUser().getImage());
            reviewDTOS.add(reviewDTO);
        });
        return  reviewDTOS;
    }

    public ArrayList<ResponseReviewDTO> changeListResponseReviewToDTO(Iterable<ResponseReview> responseReviews){
        ArrayList<ResponseReviewDTO> responseReviewDTOS=new ArrayList<>();
        responseReviews.forEach(responseReview -> {
            ResponseReviewDTO responseReviewDTO=new ResponseReviewDTO();
            responseReviewDTO.setResponseReviewId(responseReview.getResponseReviewId());
            responseReviewDTO.setDate(responseReview.getDate());
            responseReviewDTO.setContent(responseReview.getContent());
            responseReviewDTO.setName(responseReview.getUser().getName());
            responseReviewDTO.setUserId(responseReview.getUser().getUserId());
            responseReviewDTO.setImage(responseReview.getUser().getImage());
            responseReviewDTOS.add(responseReviewDTO);
        });
        return  responseReviewDTOS;
    }
}
