resource "aws_default_vpc" "shared-vpc" {
  tags = {
    Name = "Default VPC"
  }
}
resource "aws_subnet" "my-subnet" {
  vpc_id     = aws_default_vpc.shared-vpc.id
  cidr_block = "172.31.128.0/20"
  tags = {
    Name = "grupo-x-subnet"
  }
}
