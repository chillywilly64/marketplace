DROP TABLE Users IF EXISTS;
DROP TABLE Items IF EXISTS;
DROP TABLE Bids IF EXISTS ;


CREATE TABLE Users(
  User_ID BIGINT AUTO_INCREMENT NOT NULL,
  Full_Name VARCHAR2(20) NOT NULL,
  Billing_Address VARCHAR2(30) NOT NULL,
  Login VARCHAR2(20) NOT NULL,
  Password VARCHAR2(255) NOT NULL
  );

  CREATE UNIQUE INDEX PK_Users
    ON Users(User_ID);
    
  CREATE UNIQUE INDEX Users_Login
    ON Users(Login);
    
  ALTER TABLE Users
    ADD (CONSTRAINT PK_Users PRIMARY KEY (User_ID));

CREATE TABLE Items(
  Item_ID BIGINT AUTO_INCREMENT NOT NULL,
  Seller_ID  INTEGER NOT NULL,
  Title VARCHAR(30) NOT NULL,
  Description VARCHAR(50) NULL,
  Start_Price NUMBER NOT NULL,
  Time_Left NUMBER NULL,
  Start_Bidding_Date TIMESTAMP NOT NULL,
  Buy_It_Now NUMBER(1) DEFAULT 0 NOT NULL,
  Sold NUMBER(1) DEFAULT 0 NOT NULL,
  Bid_Increment NUMBER NULL
  );
  
  CREATE UNIQUE INDEX PK_Items
    ON Items(Item_ID);
    
   ALTER TABLE Items
    ADD (CONSTRAINT PK_Items PRIMARY KEY (Item_ID));
    
   ALTER TABLE Items
    ADD (CONSTRAINT FK_Items_Users
          FOREIGN KEY (Seller_ID)
          REFERENCES Users(User_ID)
          ON DELETE CASCADE);
    
  CREATE INDEX Items_Seller_ID
    ON Items(Seller_ID);
    
CREATE TABLE Bids(
  Bid_ID BIGINT AUTO_INCREMENT NOT NULL,
  Bidder_ID INTEGER NOT NULL,
  Item_ID INTEGER NOT NULL,
  Bid NUMBER NOT NULL
  );
  
  CREATE UNIQUE INDEX PK_Bids
    ON Bids(Bid_ID);
    
   ALTER TABLE Bids
    ADD (CONSTRAINT PK_Bids PRIMARY KEY (Bid_ID));
    
   ALTER TABLE Bids
    ADD (CONSTRAINT FK_Bid_Users
          FOREIGN KEY (Bidder_ID)
          REFERENCES Users(User_ID)
          ON DELETE CASCADE);
    
  ALTER TABLE Bids
    ADD (CONSTRAINT FK_Bid_Items
          FOREIGN KEY (Item_ID)
          REFERENCES Items(Item_ID)
          ON DELETE CASCADE);
    
  CREATE INDEX Bids__Bidder_ID
    ON Bids(Bidder_ID);
    
  CREATE INDEX Bids__Item_ID
    ON Bids(Item_ID);

